package org.freedom.samples.crud.modules.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author xiayx
 */
@Service
public class DemoServiceImpl implements DemoService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private DemoRepository demoRepository;

    @Override
    public Page<Demo> query(DemoParam demoParam, Pageable pageable) {
        logger.info("分页查询示例");
        return demoRepository.findAll((root, query, cb) -> {
            Set<Predicate> predicates = new LinkedHashSet<>();
            String name = StringUtils.trimWhitespace(demoParam.getName());
            if (!StringUtils.isEmpty(name)) predicates.add(cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
            if (demoParam.getBeginCreatedTime() != null) predicates.add(cb.greaterThanOrEqualTo(root.get("createdTime"), demoParam.getBeginCreatedTime()));
            if (demoParam.getEndCreatedTime() != null) predicates.add(cb.lessThanOrEqualTo(root.get("createdTime"), demoParam.getEndCreatedTime()));
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        }, pageable);
    }
}

