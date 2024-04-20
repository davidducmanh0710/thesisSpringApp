/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thesisSpringApp.pojo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ADMIN
 */
@Entity
@Table(name = "committee")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Committee.findAll", query = "SELECT c FROM Committee c"),
    @NamedQuery(name = "Committee.findById", query = "SELECT c FROM Committee c WHERE c.id = :id"),
    @NamedQuery(name = "Committee.findByName", query = "SELECT c FROM Committee c WHERE c.name = :name")})
public class Committee implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Size(max = 50)
    @Column(name = "name", length = 50)
    private String name;
    @OneToMany(mappedBy = "committeeId")
    private List<Score> scoreList;
    @OneToMany(mappedBy = "committeeId")
    private List<Member1> member1List;
    @OneToMany(mappedBy = "committeeId")
    private List<ThesisCommitteeRate> thesisCommitteeRateList;

    public Committee() {
    }

    public Committee(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public List<Score> getScoreList() {
        return scoreList;
    }

    public void setScoreList(List<Score> scoreList) {
        this.scoreList = scoreList;
    }

    @XmlTransient
    public List<Member1> getMember1List() {
        return member1List;
    }

    public void setMember1List(List<Member1> member1List) {
        this.member1List = member1List;
    }

    @XmlTransient
    public List<ThesisCommitteeRate> getThesisCommitteeRateList() {
        return thesisCommitteeRateList;
    }

    public void setThesisCommitteeRateList(List<ThesisCommitteeRate> thesisCommitteeRateList) {
        this.thesisCommitteeRateList = thesisCommitteeRateList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Committee)) {
            return false;
        }
        Committee other = (Committee) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.thesisSpringApp.pojo.Committee[ id=" + id + " ]";
    }
    
}
