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

import com.shouwy.series.bdd.model.Episode;
import com.shouwy.series.bdd.model.Saison;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Inspiron
 */
public interface EpisodeDao {
    void save(Episode entity);
    Episode merge(Episode entity);
    void update(Episode entity);
    void delete(Episode entity);
    Episode getById(Integer id);
    List<Episode> getAll();
    int count();

    public List<Episode> getByListSaison(List<Saison> listSaison);
    public List<Episode> getBySaison(Saison saison);
    public void deleteList(ArrayList<Episode> listEpisode);
}
