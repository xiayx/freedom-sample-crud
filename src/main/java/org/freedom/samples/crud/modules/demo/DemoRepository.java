package org.freedom.samples.crud.modules.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 示例资源库
 *
 * @author xiayx
 */
public interface DemoRepository extends JpaRepository<Demo, Long>, JpaSpecificationExecutor<Demo> {

}