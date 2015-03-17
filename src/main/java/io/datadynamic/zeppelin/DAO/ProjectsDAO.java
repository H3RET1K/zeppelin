/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.datadynamic.zeppelin.DAO;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

/**
 *
 * @author H3RET1K
 */
@RegisterMapper(ProjectMapper.class)
public interface ProjectsDAO
{
@SqlQuery("SELECT top 1 id, name, description, FROM projects where name = :name")
public Projects getProjectByName(@Bind("name") String name);    
}
