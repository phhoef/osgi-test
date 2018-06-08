package com.my.app.rest;

import com.my.app.rest.repository.IRepository;
import com.my.app.rest.repository.dto.Repository;
import com.my.app.rest.repository.exception.RepositoryItemNotFoundException;
import com.my.app.rest.repository.exception.RepositoryUnauthorizedException;
import com.my.app.rest.rest.ServerInfoControllerImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class UnitTest
{
    @InjectMocks ServerInfoControllerImpl _serverInfoController;
    @Mock private IRepository _repository;

    private String _repoName = "TestRepository";

    @BeforeEach
    public void initialize() throws RepositoryUnauthorizedException, RepositoryItemNotFoundException
    {
        MockitoAnnotations.initMocks(this);
        when(_repository.getRepository(anyString())).thenReturn(new Repository(_repoName));
    }

    @Test
    public void testSomething() throws RepositoryUnauthorizedException, RepositoryItemNotFoundException
    {
        Repository result = _repository.getRepository(_repoName);

        assertEquals(_repoName, result.getRepoName());
    }
}
