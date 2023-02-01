package Prospecta.Repositery;

import org.springframework.data.jpa.repository.JpaRepository;

import Prospecta.Entity.Entry;

public interface EntryRepo extends JpaRepository<Entry, Integer> {

}
