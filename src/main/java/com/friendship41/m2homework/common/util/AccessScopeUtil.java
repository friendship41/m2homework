package com.friendship41.m2homework.common.util;

import com.friendship41.m2homework.common.data.type.AccessScope;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.springframework.util.Assert;

public class AccessScopeUtil {
  public static String encodeAccessScopes(final List<AccessScope> accessScopeList) {
    if (accessScopeList == null || accessScopeList.isEmpty()) {
      return null;
    }
    return accessScopeList.stream()
        .map(Enum::name)
        .collect(Collectors.joining(","));
  }

  public static List<AccessScope> decodeAccessScopes(final String accessScopes) {
    if (accessScopes == null || accessScopes.isEmpty()) {
      return new ArrayList<>();
    }
    return Pattern.compile(",").splitAsStream(accessScopes)
        .map(s -> AccessScope.valueOf(accessScopes))
        .collect(Collectors.toList());
  }

  public static boolean hasScope(final String accessScopes, final AccessScope target) {
    Assert.notNull(accessScopes, "accessScopes is null!!");
    Assert.notNull(target, "target is null!!");

    return Pattern.compile(",").splitAsStream(accessScopes)
        .anyMatch(s -> target.name().equals(s));
  }
}
