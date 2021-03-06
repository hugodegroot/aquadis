package com.aquadis.models;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Position")
public class Position {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "place")
    private String place;

    @OneToMany(mappedBy = "beginPosition", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<RacePosition> races;

    @OneToMany(mappedBy = "endPosition", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<RacePosition> races1;

    public Position() {
    }

    public Position(String place) {
        setPlace(place);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlace() {
        return place;
    }

    private void setPlace(String place) {
        this.place = place;
    }

    public List<RacePosition> getRaces() {
        return races;
    }

    public void setRaces(List<RacePosition> races) {
        this.races = races;
    }

    public List<RacePosition> getRaces1() {
        return races1;
    }

    public void setRaces1(List<RacePosition> races1) {
        this.races1 = races1;
    }
}
