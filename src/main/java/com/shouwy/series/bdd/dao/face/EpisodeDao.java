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

import com.shouwy.series.bdd.dao.generic.GenericDao;
import com.shouwy.series.bdd.model.Episode;
import com.shouwy.series.bdd.model.Saison;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Inspiron
 */
public interface EpisodeDao extends GenericDao<Episode>{

    public List<Episode> getByListSaison(List<Saison> listSaison);
    public List<Episode> getBySaison(Saison saison);
    public void deleteList(ArrayList<Episode> listEpisode);
}
