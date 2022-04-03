package com.nrifintech.lms.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nrifintech.lms.entity.Publisher;
import com.nrifintech.lms.repository.PublisherRepository;
import com.nrifintech.lms.service.PublisherService;
@Service
public class PublisherServiceImpl implements PublisherService {
	@Autowired
	PublisherRepository publisherRepository;

	@Override
	public Publisher findById(int id) {
		Optional<Publisher> optionalPublisher = publisherRepository.findById(id);
		return optionalPublisher.get();
	}
	
}
