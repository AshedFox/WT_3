package bsuir.wt.lab3.service;

import bsuir.wt.lab3.entity.ArchiveFile;
import bsuir.wt.lab3.service.exception.ServiceException;

import java.util.List;

public interface ArchiveFileService {
    void Create(ArchiveFile newFile) throws ServiceException;
    ArchiveFile Read(int id) throws ServiceException;
    List<ArchiveFile> ReadList() throws ServiceException;
    void Update(int id, ArchiveFile newFile) throws ServiceException;
    void Delete(int id) throws ServiceException;
}
