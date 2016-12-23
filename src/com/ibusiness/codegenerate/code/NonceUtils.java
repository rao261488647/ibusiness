package com.ibusiness.codegenerate.code;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;

public class NonceUtils
{
  private static final SimpleDateFormat ymdhms = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
  private static final String[] jdField_a_of_type_ArrayOfJavaLangString = { "0", "00", "0000", "00000000" };
  private static Date date;
  private static int typeInt = 0;

  public static String randomString(int paramInt)
  {
    return RandomStringUtils.randomAlphanumeric(paramInt);
  }

  public static int randomInt()
  {
    return new SecureRandom().nextInt();
  }

  public static String randomHexInt()
  {
    return Integer.toHexString(randomInt());
  }

  public static long randomLong()
  {
    return new SecureRandom().nextLong();
  }

  public static String randomHexLong()
  {
    return Long.toHexString(randomLong());
  }

  public static String randomUUID()
  {
    return UUID.randomUUID().toString();
  }

  public static String currentTimestamp()
  {
    Date localDate = new Date();
    return ymdhms.format(localDate);
  }

  public static long currentMills()
  {
    return System.currentTimeMillis();
  }

  public static String currentHexMills()
  {
    return Long.toHexString(currentMills());
  }

  public static synchronized String getCounter()
  {
    Date localDate = new Date();
    if (localDate.equals(date))
    {
      typeInt += 1;
    }
    else
    {
      date = localDate;
      typeInt = 0;
    }
    return Integer.toHexString(typeInt);
  }

  public static String format(String paramString, int paramInt)
  {
    int i = paramInt - paramString.length();
    StringBuilder localStringBuilder = new StringBuilder();
    while (i >= 8)
    {
      localStringBuilder.append(jdField_a_of_type_ArrayOfJavaLangString[3]);
      i -= 8;
    }
    for (int j = 2; j >= 0; --j)
    {
      if ((i & 1 << j) == 0)
        continue;
      localStringBuilder.append(jdField_a_of_type_ArrayOfJavaLangString[j]);
    }
    localStringBuilder.append(paramString);
    return localStringBuilder.toString();
  }

  public static void main(String[] paramArrayOfString)
  {
    System.out.println(randomLong() + currentMills());
  }
}
