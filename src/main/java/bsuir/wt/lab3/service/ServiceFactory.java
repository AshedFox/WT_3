package bsuir.wt.lab3.service;

import bsuir.wt.lab3.service.impl.ArchiveFileServiceImpl;
import bsuir.wt.lab3.service.impl.UserServiceImpl;

public final class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();
    private final ArchiveFileService archiveFileService = new ArchiveFileServiceImpl();
    private final UserService userService = new UserServiceImpl();

    public static ServiceFactory getInstance() {
        return instance;
    }

    public ArchiveFileService getArchiveFileService() {
        return archiveFileService;
    }

    public UserService getUserService() {
        return userService;
    }
}
