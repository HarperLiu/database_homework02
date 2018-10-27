package entites;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "data_record", schema = "homework02", catalog = "")
public class DataRecordEntity {
    private int id;
    private String username;
    private int dataUsage;
    private Date creationDate;
    private String dataType;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "username", nullable = false, length = 60)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "data_usage", nullable = false)
    public int getDataUsage() {
        return dataUsage;
    }

    public void setDataUsage(int dataUsage) {
        this.dataUsage = dataUsage;
    }

    @Basic
    @Column(name = "creation_date", nullable = false)
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Basic
    @Column(name = "data_type", nullable = false, length = 60)
    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DataRecordEntity that = (DataRecordEntity) o;

        if (id != that.id) return false;
        if (dataUsage != that.dataUsage) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (creationDate != null ? !creationDate.equals(that.creationDate) : that.creationDate != null) return false;
        if (dataType != null ? !dataType.equals(that.dataType) : that.dataType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + dataUsage;
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        result = 31 * result + (dataType != null ? dataType.hashCode() : 0);
        return result;
    }
}
