package com.houkunlin.system.dict.starter.provider;

import com.houkunlin.system.dict.starter.SystemDictScan;
import com.houkunlin.system.dict.starter.bean.DictTypeVo;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 系统字典提供者。{@link SystemDictScan} 注解扫描到的系统字典都会写入到该对象中。
 * 然后由指定的程序获取系统中所有 {@link DictProvider} 对象列表对所有字典进行注册
 *
 * @author HouKunLin
 */
@Getter
@Component
public class SystemDictProvider implements DictProvider {
    private static final Logger logger = LoggerFactory.getLogger(SystemDictProvider.class);
    private final Map<String, DictTypeVo> cache = new HashMap<>();

    /**
     * 增加一个字典类型对象（含字典值列表）
     *
     * @param vo 字典类型对象
     */
    public void addDict(final DictTypeVo vo) {
        vo.setChildren(new ArrayList<>(vo.getChildren()));
        cache.put(vo.getType(), vo);
    }

    @Override
    public Iterator<DictTypeVo> dictTypeIterator() {
        final Collection<DictTypeVo> values = cache.values();
        if (logger.isDebugEnabled()) {
            logger.debug("当前系统共有 {} 个系统字典类型信息", values.size());
        }
        return values.iterator();
    }
}
