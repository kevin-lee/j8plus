package cc.kevinlee.functional.data;

public class TestBean {
  final String name;

  public TestBean(final String name) {
    this.name = name;
  }

  public boolean isOk(@SuppressWarnings("unused") final Integer number) {
    return false;
  }

  public boolean isOk(@SuppressWarnings("unused") final Integer number, @SuppressWarnings("unused") final String prefix) {
    return false;
  }

  public boolean isOk(@SuppressWarnings("unused") final Integer number, @SuppressWarnings("unused") final String prefix,
               @SuppressWarnings("unused") final String suffix) {
    return false;
  }

  public boolean isOk(@SuppressWarnings("unused") final Integer number, @SuppressWarnings("unused") final String prefix,
               @SuppressWarnings("unused") final String suffix, @SuppressWarnings("unused") final String param4) {
    return false;
  }

  public boolean isOk(@SuppressWarnings("unused") final Integer number, @SuppressWarnings("unused") final String prefix,
               @SuppressWarnings("unused") final String suffix, @SuppressWarnings("unused") final String param4,
               @SuppressWarnings("unused") final String param5) {
    return false;
  }

  public boolean isOk(@SuppressWarnings("unused") final Integer number, @SuppressWarnings("unused") final String prefix,
               @SuppressWarnings("unused") final String suffix, @SuppressWarnings("unused") final String param4,
               @SuppressWarnings("unused") final String param5, @SuppressWarnings("unused") final String param6) {
    return false;
  }

  public boolean isOk(@SuppressWarnings("unused") final Integer number, @SuppressWarnings("unused") final String prefix,
               @SuppressWarnings("unused") final String suffix, @SuppressWarnings("unused") final String param4,
               @SuppressWarnings("unused") final String param5, @SuppressWarnings("unused") final String param6,
               @SuppressWarnings("unused") final String param7) {
    return false;
  }

  public boolean isOk(@SuppressWarnings("unused") final Integer number, @SuppressWarnings("unused") final String prefix,
               @SuppressWarnings("unused") final String suffix, @SuppressWarnings("unused") final String param4,
               @SuppressWarnings("unused") final String param5, @SuppressWarnings("unused") final String param6,
               @SuppressWarnings("unused") final String param7, @SuppressWarnings("unused") final String param8) {
    return false;
  }

  public boolean isOk(@SuppressWarnings("unused") final Integer number, @SuppressWarnings("unused") final String prefix,
               @SuppressWarnings("unused") final String suffix, @SuppressWarnings("unused") final String param4,
               @SuppressWarnings("unused") final String param5, @SuppressWarnings("unused") final String param6,
               @SuppressWarnings("unused") final String param7, @SuppressWarnings("unused") final String param8,
               @SuppressWarnings("unused") final String param9) {
    return false;
  }

  public String call(final Integer number) {
    return name + "+" + number;
  }

  public String call(final Integer number, final String prefix) {
    return prefix + ":" + name + "+" + number;
  }

  public String call(final Integer number, final String prefix, final String suffix) {
    return prefix + ":" + name + "+" + number + suffix;
  }

  public String call(final Integer number, final String prefix, final String suffix, final String param4) {
    return prefix + ":" + name + "+" + number + suffix + " - " + param4;
  }

  public String call(final Integer number, final String prefix, final String suffix, final String param4, final String param5) {
    return prefix + ":" + name + "+" + number + suffix + " - " + param4 + " - " + param5;
  }

  public String call(final Integer number, final String prefix, final String suffix, final String param4, final String param5,
              final String param6) {
    return prefix + ":" + name + "+" + number + suffix + " - " + param4 + " - " + param5 + "-" + param6;
  }

  public String call(final Integer number, final String prefix, final String suffix, final String param4, final String param5,
              final String param6, final String param7) {
    return prefix + ":" + name + "+" + number + suffix + " - " + param4 + " - " + param5 + "-" + param6 + "-" + param7;
  }

  public String call(final Integer number, final String prefix, final String suffix, final String param4, final String param5,
              final String param6, final String param7, final String param8) {
    return prefix + ":" + name + "+" + number + suffix + " - " + param4 + " - " + param5 + "-" + param6 + "-" + param7 + "-" + param8;
  }

  public String call(final Integer number, final String prefix, final String suffix, final String param4, final String param5,
              final String param6, final String param7, final String param8, final String param9) {
    return prefix + ":" + name + "+" + number + suffix + " - " + param4 + " - " + param5 + "-" + param6 + "-" + param7 + "-" + param8
        + "-" + param9;
  }

  public void run(@SuppressWarnings("unused") final Integer number) {
    throw new AssertionError("Use mock to test");
  }

  public void run(@SuppressWarnings("unused") final Integer number, @SuppressWarnings("unused") final String prefix) {
    throw new AssertionError("Use mock to test");
  }

  public void run(@SuppressWarnings("unused") final Integer number, @SuppressWarnings("unused") final String prefix,
           @SuppressWarnings("unused") final String suffix) {
    throw new AssertionError("Use mock to test");
  }

  public void run(@SuppressWarnings("unused") final Integer number, @SuppressWarnings("unused") final String prefix,
           @SuppressWarnings("unused") final String suffix, @SuppressWarnings("unused") final String param4) {
    throw new AssertionError("Use mock to test");
  }

  public void run(@SuppressWarnings("unused") final Integer number, @SuppressWarnings("unused") final String prefix,
           @SuppressWarnings("unused") final String suffix, @SuppressWarnings("unused") final String param4,
           @SuppressWarnings("unused") final String param5) {
    throw new AssertionError("Use mock to test");
  }

  public void run(@SuppressWarnings("unused") final Integer number, @SuppressWarnings("unused") final String prefix,
           @SuppressWarnings("unused") final String suffix, @SuppressWarnings("unused") final String param4,
           @SuppressWarnings("unused") final String param5, @SuppressWarnings("unused") final String param6) {
    throw new AssertionError("Use mock to test");
  }

  public void run(@SuppressWarnings("unused") final Integer number, @SuppressWarnings("unused") final String prefix,
           @SuppressWarnings("unused") final String suffix, @SuppressWarnings("unused") final String param4,
           @SuppressWarnings("unused") final String param5, @SuppressWarnings("unused") final String param6,
           @SuppressWarnings("unused") final String param7) {
    throw new AssertionError("Use mock to test");
  }

  public void run(@SuppressWarnings("unused") final Integer number, @SuppressWarnings("unused") final String prefix,
           @SuppressWarnings("unused") final String suffix, @SuppressWarnings("unused") final String param4,
           @SuppressWarnings("unused") final String param5, @SuppressWarnings("unused") final String param6,
           @SuppressWarnings("unused") final String param7, @SuppressWarnings("unused") final String param8) {
    throw new AssertionError("Use mock to test");
  }

  public void run(@SuppressWarnings("unused") final Integer number, @SuppressWarnings("unused") final String prefix,
           @SuppressWarnings("unused") final String suffix, @SuppressWarnings("unused") final String param4,
           @SuppressWarnings("unused") final String param5, @SuppressWarnings("unused") final String param6,
           @SuppressWarnings("unused") final String param7, @SuppressWarnings("unused") final String param8,
           @SuppressWarnings("unused") final String param9) {
    throw new AssertionError("Use mock to test");
  }
}