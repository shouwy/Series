/*
 * Copyright (C) 2013 Inspiron
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.shouwy.series.bdd.model;

import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Inspiron
 */
@Entity
public class Episode  {
    @Id
    @GeneratedValue
    Integer id;
    private String titre;
    private String synopsis;
    private int idSaison;
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dateSortie; 
    private int idEtatPersonnel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public int getIdSaison() {
        return idSaison;
    }

    public void setIdSaison(int idSaison) {
        this.idSaison = idSaison;
    }

    public int getIdEtatPersonnel() {
        return idEtatPersonnel;
    }

    public void setIdEtatPersonnel(int idEtatPersonnel) {
        this.idEtatPersonnel = idEtatPersonnel;
    }

    public Calendar getDateSortie() {
        return dateSortie;
    }

    public void setDateSortie(Calendar dateSortie) {
        this.dateSortie = dateSortie;
    }
}
