package com.example.demo.dao;

import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository("postges")
public class PersonDataAccessService implements PersonDao{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public int insertPerson(UUID id, Person person) {

        final String sql = "INSERT INTO person(id,name) VALUES ('?','?')";
//        jdbcTemplate.execute(sql,id,person.getName());
        jdbcTemplate.update(sql,id,person.getName());

        return 0;
    }

    @Override
    public List<Person> selectAllPerson() {
        final String sql = "SELECT id,name FROM person" ;
        List <Person> people = jdbcTemplate.query(sql,(resultSet,i) -> {
            UUID id = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            return  new Person(
                    id,name);
        });
        return people;
//        return Arrays.asList(new Person(UUID.randomUUID(),"From Postges DB"));
    }


    @Override
    public Optional<Person> selectPersonById(UUID id) {
        final String sql = "SELECT id,name FROM person WHERE id = ?" ;

        Person  person = jdbcTemplate.queryForObject(sql,new Object[]{id},(resultSet, i) -> {
            UUID retriveId = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            return  new Person(
                    retriveId,name);
        });
        return Optional.ofNullable(person);
    }

    @Override
    public int deletePersonById(UUID id) {
        final String sql = "DELETE FROM person WHERE id = ?" ;
        jdbcTemplate.update(sql,id);
        return 0;
    }

    @Override
    public int updatePersonById(UUID id, Person person) {
        final String sql = "UPDATE person SET name = ? WHERE id = ?";
        jdbcTemplate.update(sql,new Object[]{person.getName(),id});

        return 0;
    }
}
