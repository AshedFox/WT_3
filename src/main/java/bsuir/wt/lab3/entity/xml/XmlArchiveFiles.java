package bsuir.wt.lab3.entity.xml;

import bsuir.wt.lab3.entity.ArchiveFile;
import jakarta.xml.bind.annotation.*;

import java.util.List;

@XmlRootElement(name = "archiveFiles")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlArchiveFiles {

    @XmlElements({
            @XmlElement(name = "archiveFile", type = ArchiveFile.class)
    })
    private List<ArchiveFile> archiveFiles;

    public XmlArchiveFiles() {}

    public List<ArchiveFile> getArchiveFiles() {
        return archiveFiles;
    }

    public void setArchiveFiles(List<ArchiveFile> archiveFiles) {
        this.archiveFiles = archiveFiles;
    }
}
