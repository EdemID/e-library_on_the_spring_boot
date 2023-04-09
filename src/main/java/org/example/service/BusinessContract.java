package org.example.service;

import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface BusinessContract<T, I> {

    T findById(final I id);

    @Transactional
    I save(final T dto);

    @Transactional
    T update(final I id, final T updatedDto);

    @Transactional
    void delete(final I id);

}
