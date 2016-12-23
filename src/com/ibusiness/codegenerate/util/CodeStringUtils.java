package com.ibusiness.codegenerate.util;

import org.apache.commons.lang3.StringUtils;

public class CodeStringUtils
{
  public static String getStringSplit(String[] paramArrayOfString)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    String[] arrayOfString = paramArrayOfString;
    int j = paramArrayOfString.length;
    for (int i = 0; i < j; ++i)
    {
      String str = arrayOfString[i];
      if (!StringUtils.isNotBlank(str))
        continue;
      localStringBuffer.append(",");
      localStringBuffer.append("'");
      localStringBuffer.append(str.trim());
      localStringBuffer.append("'");
    }
    return localStringBuffer.toString().substring(1);
  }

  public static String getInitialSmall(String paramString)
  {
    if (StringUtils.isNotBlank(paramString))
      paramString = paramString.substring(0, 1).toLowerCase() + paramString.substring(1);
    return paramString;
  }

  public static Integer getIntegerNotNull(Integer paramInteger)
  {
    if (paramInteger == null)
      return Integer.valueOf(0);
    return paramInteger;
  }
}

/* Location:           C:\Users\Administrator\Desktop\commons-cg-2.1.jar
 * Qualified Name:     com.util.CodeStringUtils
 * JD-Core Version:    0.5.4
 */