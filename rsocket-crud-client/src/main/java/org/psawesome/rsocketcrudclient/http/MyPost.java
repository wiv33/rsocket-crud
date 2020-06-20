package org.psawesome.rsocketcrudclient.http;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MyPost {

  private Long id;

  private String title;

  private String content;
}
