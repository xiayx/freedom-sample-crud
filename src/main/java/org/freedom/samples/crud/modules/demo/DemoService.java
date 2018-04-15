package org.freedom.samples.crud.modules.demo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author xiayx
 */
public interface DemoService {

    /** 分页查询 */
    Page<Demo> query(DemoParam demoParam, Pageable pageable);
}
