package entites;

import javax.persistence.*;

@Entity
@Table(name = "discount_package", schema = "homework02", catalog = "")
public class DiscountPackageEntity {
    private int pid;
    private int phone;
    private int message;
    private int domesticData;
    private int localData;
    private int base;

    @Id
    @Column(name = "pid", nullable = false)
    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    @Basic
    @Column(name = "phone", nullable = false)
    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "message", nullable = false)
    public int getMessage() {
        return message;
    }

    public void setMessage(int message) {
        this.message = message;
    }

    @Basic
    @Column(name = "domestic_data", nullable = false)
    public int getDomesticData() {
        return domesticData;
    }

    public void setDomesticData(int domesticData) {
        this.domesticData = domesticData;
    }

    @Basic
    @Column(name = "local_data", nullable = false)
    public int getLocalData() {
        return localData;
    }

    public void setLocalData(int localData) {
        this.localData = localData;
    }

    @Basic
    @Column(name = "base", nullable = false)
    public int getBase() {
        return base;
    }

    public void setBase(int base) {
        this.base = base;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DiscountPackageEntity that = (DiscountPackageEntity) o;

        if (pid != that.pid) return false;
        if (phone != that.phone) return false;
        if (message != that.message) return false;
        if (domesticData != that.domesticData) return false;
        if (localData != that.localData) return false;
        if (base != that.base) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pid;
        result = 31 * result + phone;
        result = 31 * result + message;
        result = 31 * result + domesticData;
        result = 31 * result + localData;
        result = 31 * result + base;
        return result;
    }
}
