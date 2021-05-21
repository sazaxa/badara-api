package com.sazaxa.shipmentapi.faq;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import com.amazonaws.services.s3.transfer.Upload;
import com.sazaxa.shipmentapi.config.AwsConfig;
import com.sazaxa.shipmentapi.faq.dto.FaqSaveRequestDto;
import com.sazaxa.shipmentapi.faq.dto.FaqUpdateRequestDto;
import com.sazaxa.shipmentapi.faq.dto.ImgResponseDto;
import com.sazaxa.shipmentapi.faq.errors.FaqNotFoundException;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Transactional
@Service
public class FaqService {

    private final FaqRepository faqRepository;
    private final AwsConfig awsConfig;
    private final String keyName;

    public FaqService(FaqRepository faqRepository, AwsConfig awsConfig) {
        this.faqRepository = faqRepository;
        this.awsConfig = awsConfig;
        this.keyName = "faq/";
    }

    public List<Faq> list() {
        return faqRepository.findAll();
    }

    public Faq detail(Long id) {
        Faq faq = faqRepository.findById(id).orElseThrow(() -> new FaqNotFoundException("no id" + id) );
        return faq;
    }

    public Faq create(FaqSaveRequestDto request) {
        Faq faq = Faq.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .build();
        return faqRepository.save(faq);
    }

    public Faq update(Long id, FaqUpdateRequestDto faqUpdateRequestDto) {
        Faq faq = faqRepository.findById(id).orElseThrow(() -> new FaqNotFoundException("no id" + id) );
        faq.updateFaq(faqUpdateRequestDto.getTitle(), faqUpdateRequestDto.getContent());
        faqRepository.save(faq);
        return faq;
    }

    public Faq delete(Long id) {
        Faq faq = faqRepository.findById(id).orElseThrow(() -> new FaqNotFoundException("no id" + id) );
        faqRepository.delete(faq);
        return faq;
    }

    public ImgResponseDto uploadImage(MultipartFile image) {
        String imgUrl = RandomStringUtils.randomAlphanumeric(12);
        BasicAWSCredentials awsCreds = new BasicAWSCredentials(awsConfig.getAccessKeyId(), awsConfig.getSecretAccessKey());
        try {
            AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                    .withRegion(Regions.AP_NORTHEAST_2)
                    .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                    .build();

            TransferManager tm = TransferManagerBuilder.standard()
                    .withS3Client(s3Client)
                    .build();

            File uploadImg = createUploadImg(image);

            Upload upload = tm.upload(awsConfig.getBucketName(), keyName + imgUrl, uploadImg);
            upload.waitForCompletion();

            Files.delete(Paths.get(image.getOriginalFilename()));

        } catch (AmazonServiceException e) {
            e.printStackTrace();
        } catch (SdkClientException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ImgResponseDto(imgUrl);
    }

    private File createUploadImg(MultipartFile image) throws IOException {
        File file = new File(image.getOriginalFilename());
        file.createNewFile();
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(image.getBytes());
        fos.close();
        return file;
    }
}
