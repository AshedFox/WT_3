package bsuir.wt.lab3.dao;

import bsuir.wt.lab3.dao.exception.DAOException;
import bsuir.wt.lab3.entity.ArchiveFile;

import java.util.List;

public interface ArchiveFileDAO {
    void Create(ArchiveFile newFile) throws DAOException;
    ArchiveFile Read(int id) throws DAOException;
    List<ArchiveFile> ReadList() throws DAOException;
    void Update(int id, ArchiveFile newFile) throws DAOException;
    void Delete(int id) throws DAOException;
}
