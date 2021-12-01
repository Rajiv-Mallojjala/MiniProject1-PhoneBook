package com.rajivit.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rajivit.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact, Serializable> {

}
