package com.springapi.flickrfeed;

import com.springapi.flickrfeed.model.ImagesResponseVO;
import com.springapi.flickrfeed.service.ImagesService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class FlickrfeedApplicationTests {

	@Autowired
	ImagesService imgsService;

	@MockBean
	RestTemplate restTemplate;

	@Autowired
	ResourceLoader	resourceLoader;

	@Test
	void ValidResponse_WhenGetFlickrFeedData_ExpectSuccess() {

		String data = null;

		try{
			Resource resource = resourceLoader.getResource("classpath:response.txt");
			InputStream input = resource.getInputStream();
			File file = resource.getFile();

			byte[] bytedata = FileCopyUtils.copyToByteArray(input);
			data = new String(bytedata, StandardCharsets.UTF_8);
		}catch (IOException e){
			data = null;
			System.out.println("error_read_data");
		}

		ResponseEntity<String> mockedResponse = new ResponseEntity<String>(data,HttpStatus.ACCEPTED);
		when(restTemplate.exchange(
				ArgumentMatchers.anyString(),
				ArgumentMatchers.any(HttpMethod.class),
				ArgumentMatchers.any(),
				ArgumentMatchers.<Class<String>>any()))
				.thenReturn(mockedResponse);

		List<ImagesResponseVO> result = imgsService.getFlickrFeedData();
		assertThat(result.size()).isEqualTo(40);
	}

	@Test
	void ValidResponse_WhenGetFlickrFeedDataByTags_ExpectSuccess() {

		String data = null;

		try{
			Resource resource = resourceLoader.getResource("classpath:responseOwls.txt");
			InputStream input = resource.getInputStream();
			File file = resource.getFile();

			byte[] bdata = FileCopyUtils.copyToByteArray(input);
			data = new String(bdata, StandardCharsets.UTF_8);

		}catch (IOException e){
			data = null;
			System.out.println("error_read_data");
		}

		ResponseEntity<String> mockedResponse = new ResponseEntity<String>(data,HttpStatus.ACCEPTED);
		when(restTemplate.exchange(
				ArgumentMatchers.anyString(),
				ArgumentMatchers.any(HttpMethod.class),
				ArgumentMatchers.any(),
				ArgumentMatchers.<Class<String>>any()))
				.thenReturn(mockedResponse);

		List<ImagesResponseVO> result = imgsService.getFlickrFeedDataByTags("owl");
		assertThat(result.get(0).getTags().contains("owl")).isEqualTo(true);
	}

	@Test
	void ValidResponseFromStoredFeedData_ExpectSuccess(){

		List<ImagesResponseVO> result = imgsService.getAllStoredFeedData();
		assertThat(result.size()).isEqualTo(20);
	}

	@Test
	void ValidResponseFromStoredFeedData_ExpectEmpty(){

		imgsService.clearStoredFeedData();

		List<ImagesResponseVO> result = imgsService.getAllStoredFeedData();
		assertThat(result.size()).isEqualTo(0);
	}

	@Test
	void ValidResponseFromStoredFeedDataByTags_ExpectSuccess(){

		List<ImagesResponseVO> result = imgsService.getStoredFeedDataByTags("owl");
		assertThat(result.get(0).getTags().contains("owl")).isEqualTo(true);
	}
}
