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
package com.shouwy.series.bdd.dao.face;

import com.shouwy.series.bdd.model.Etat;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Inspiron
 */
public interface EtatDao {
    void save(Etat entity);
    Etat merge(Etat entity);
    void update(Etat entity);
    void delete(Etat entity);
    Etat getById(Integer id);
    List<Etat> getAll();
    Map<Integer, Etat> getMapAll();
    int count();
}
