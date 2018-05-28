package com.my.app.repository;

import com.my.app.repository.exception.RepositoryItemNotFoundException;
import com.my.app.repository.exception.RepositoryUnauthorizedException;
import com.my.app.repository.dto.Repository;

public interface IRepository
{
    Repository getRepository(String repoName) throws RepositoryUnauthorizedException, RepositoryItemNotFoundException;
}
