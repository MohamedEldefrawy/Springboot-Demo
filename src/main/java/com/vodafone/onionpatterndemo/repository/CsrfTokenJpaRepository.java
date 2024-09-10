package com.vodafone.onionpatterndemo.repository;

import com.vodafone.onionpatterndemo.model.CsrfToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CsrfTokenJpaRepository extends JpaRepository<CsrfToken, String> {}
