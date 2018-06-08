package com.my.app.rest.repository;

import com.my.app.rest.repository.exception.RepositoryItemNotFoundException;
import com.my.app.rest.repository.exception.RepositoryUnauthorizedException;
import com.my.app.rest.repository.dto.Repository;

public interface IRepository
{
    Repository getRepository(String repoName) throws RepositoryUnauthorizedException, RepositoryItemNotFoundException;
}
