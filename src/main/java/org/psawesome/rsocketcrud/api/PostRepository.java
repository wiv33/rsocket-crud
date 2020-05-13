package org.psawesome.rsocketcrud.api;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface PostRepository extends R2dbcRepository<MyPost, Long> {
}
