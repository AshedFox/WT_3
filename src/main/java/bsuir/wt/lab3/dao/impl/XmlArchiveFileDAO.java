package bsuir.wt.lab3.dao.impl;

import bsuir.wt.lab3.dao.ArchiveFileDAO;
import bsuir.wt.lab3.dao.exception.DAOException;
import bsuir.wt.lab3.entity.ArchiveFile;
import bsuir.wt.lab3.entity.xml.XmlArchiveFiles;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class XmlArchiveFileDAO implements ArchiveFileDAO {
    @Override
    public void Create(ArchiveFile newFile) throws DAOException {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(XmlArchiveFiles.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            XmlArchiveFiles xmlArchiveFiles;

            try (BufferedReader stream = Files.newBufferedReader(Path.of("src/main/resources/archiveFiles.xml"))) {
                xmlArchiveFiles = (XmlArchiveFiles) jaxbUnmarshaller.unmarshal(stream);

                List<ArchiveFile> archiveFiles = xmlArchiveFiles.getArchiveFiles();
                newFile.setId(archiveFiles.get(archiveFiles.size() - 1).getId() + 1);

                archiveFiles.add(newFile);
                xmlArchiveFiles.setArchiveFiles(archiveFiles);
            }

            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            try (BufferedWriter stream = Files.newBufferedWriter(Path.of("src/main/resources/archiveFiles.xml"))) {
                jaxbMarshaller.marshal(xmlArchiveFiles, stream);
            }
        } catch (Exception e) {
            throw new DAOException("something wrong");
       }
    }

    @Override
    public ArchiveFile Read(int id) throws DAOException {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(XmlArchiveFiles.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            XmlArchiveFiles xmlArchiveFiles =
                    (XmlArchiveFiles)jaxbUnmarshaller.unmarshal(
                            Files.newBufferedReader(Path.of("src/main/resources/archiveFiles.xml"))
                    );


            List<ArchiveFile> archiveFiles = xmlArchiveFiles.getArchiveFiles();

            var archiveFile = archiveFiles.stream().filter(af -> af.getId() == id).findFirst();

            if (archiveFile.isEmpty()) {
                throw new DAOException("archive file not found");
            }

            return archiveFile.get();
        } catch (Exception e) {
            throw new DAOException("something wrong");
        }
    }

    @Override
    public List<ArchiveFile> ReadList() throws DAOException {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(XmlArchiveFiles.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            XmlArchiveFiles xmlArchiveFiles =
                    (XmlArchiveFiles)jaxbUnmarshaller.unmarshal(
                            Files.newBufferedReader(Path.of("src/main/resources/archiveFiles.xml"))
                    );

            return xmlArchiveFiles.getArchiveFiles();
        } catch (Exception e) {
            throw new DAOException("something wrong");
        }
    }

    @Override
    public void Update(int id, ArchiveFile newFile) throws DAOException {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(XmlArchiveFiles.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            XmlArchiveFiles xmlArchiveFiles =
                    (XmlArchiveFiles)jaxbUnmarshaller.unmarshal(
                            Files.newBufferedReader(Path.of("src/main/resources/archiveFiles.xml"))
                    );
            List<ArchiveFile> archiveFiles = xmlArchiveFiles.getArchiveFiles();
            var archiveFile =
                    archiveFiles.stream().filter(af -> af.getId() == id).findFirst();

            if (archiveFile.isEmpty()) {
                throw new DAOException("archive file not found");
            }

            newFile.setId(archiveFile.get().getId());
            archiveFiles.set(archiveFiles.indexOf(archiveFile.get()), newFile);
            xmlArchiveFiles.setArchiveFiles(archiveFiles);

            jaxbContext = JAXBContext.newInstance(XmlArchiveFiles.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            jaxbMarshaller.marshal(xmlArchiveFiles, Files.newBufferedWriter(Path.of("src/main/resources/archiveFiles.xml")));
        } catch (Exception e) {
            throw new DAOException("something wrong");
        }
    }

    @Override
    public void Delete(int id) throws DAOException {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(XmlArchiveFiles.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            XmlArchiveFiles xmlArchiveFiles =
                    (XmlArchiveFiles)jaxbUnmarshaller.unmarshal(
                            Files.newBufferedReader(Path.of("src/main/resources/archiveFiles.xml"))
                    );
            List<ArchiveFile> archiveFiles = xmlArchiveFiles.getArchiveFiles();
            var archiveFile =
                    archiveFiles.stream().filter(af -> af.getId() == id).findFirst();

            if (archiveFile.isEmpty()) {
                throw new DAOException("archive file not found");
            }

            archiveFiles.remove(archiveFile.get());

            xmlArchiveFiles.setArchiveFiles(archiveFiles);

            jaxbContext = JAXBContext.newInstance(XmlArchiveFiles.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            jaxbMarshaller.marshal(xmlArchiveFiles, Files.newBufferedWriter(Path.of("src/main/resources/archiveFiles.xml")));
        } catch (Exception e) {
            throw new DAOException("something wrong");
        }
    }
}
