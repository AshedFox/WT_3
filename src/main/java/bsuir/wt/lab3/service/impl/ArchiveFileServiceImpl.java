package bsuir.wt.lab3.service.impl;

import bsuir.wt.lab3.dao.DAOFactory;
import bsuir.wt.lab3.dao.exception.DAOException;
import bsuir.wt.lab3.entity.ArchiveFile;
import bsuir.wt.lab3.service.ArchiveFileService;
import bsuir.wt.lab3.service.exception.ServiceException;

import java.util.List;

public class ArchiveFileServiceImpl implements ArchiveFileService {

    @Override
    public void Create(ArchiveFile newFile) throws ServiceException {
        var archiveFileDAO = DAOFactory.getInstance().getArchiveFileDAO();

        try {
            archiveFileDAO.Create(newFile);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public ArchiveFile Read(int id) throws ServiceException {
        var archiveFileDAO = DAOFactory.getInstance().getArchiveFileDAO();

        try {
            return archiveFileDAO.Read(id);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public List<ArchiveFile> ReadList() throws ServiceException {
        var archiveFileDAO = DAOFactory.getInstance().getArchiveFileDAO();

        try {
            return archiveFileDAO.ReadList();
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void Update(int id, ArchiveFile newFile) throws ServiceException {
        var archiveFileDAO = DAOFactory.getInstance().getArchiveFileDAO();

        try {
            archiveFileDAO.Update(id, newFile);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void Delete(int id) throws ServiceException {
        var archiveFileDAO = DAOFactory.getInstance().getArchiveFileDAO();

        try {
            archiveFileDAO.Delete(id);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
