package com.my.app.repository.dto;

public class Repository
{
    private String _repoName;

    public Repository(String repoName)
    {
        _repoName = repoName;
    }

    public String getRepoName()
    {
        return _repoName;
    }
}
