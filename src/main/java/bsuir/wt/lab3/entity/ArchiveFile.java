package bsuir.wt.lab3.entity;

import jakarta.xml.bind.annotation.*;
import java.util.StringJoiner;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "archiveFile", propOrder = {
        "id", "code", "student"
})
public class ArchiveFile {
    private int id;
    private String code;
    private Student student;

    public ArchiveFile() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArchiveFile that)) return false;

        if (id != that.id) return false;
        if (!code.equals(that.code)) return false;
        return student.equals(that.student);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + code.hashCode();
        result = 31 * result + student.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ArchiveFile.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("code='" + code + "'")
                .add("student=" + student)
                .toString();
    }

    public static ArchiveFile parse(String str) {
        var archiveFile = new ArchiveFile();

        var paramsString = str.substring("ArchiveFile".length(), str.length() - 1);
        var params = paramsString.split(", ");

        archiveFile.setId(Integer.parseInt(params[0].substring(params[0].indexOf("=") + 1)));
        archiveFile.setCode(params[1].substring(params[1].indexOf("=") + 2, params[1].length() - 1));
        archiveFile.setStudent(Student.parse(params[2].substring(params[2].indexOf("=") + 1)));

        return archiveFile;
    }
}
