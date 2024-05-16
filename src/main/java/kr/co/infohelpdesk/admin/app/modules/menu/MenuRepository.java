package kr.co.infohelpdesk.admin.app.modules.menu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface MenuRepository extends JpaRepository<Menu, Integer>, QuerydslPredicateExecutor<Menu> {

}

