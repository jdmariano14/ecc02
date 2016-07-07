package com.exist.ecc.matrix.model.impl;

import com.exist.ecc.matrix.model.api.CharDomain;

public class AsciiCharDomain implements CharDomain {
  public boolean contains(char c) {
    return (String.valueOf(c)).matches("\\A\\p{ASCII}*\\z");
  }
}