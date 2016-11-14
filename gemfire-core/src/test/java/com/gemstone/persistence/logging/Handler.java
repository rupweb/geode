/*=========================================================================
 * Copyright (c) 2010-2014 Pivotal Software, Inc. All Rights Reserved.
 * This product is protected by U.S. and international copyright
 * and intellectual property laws. Pivotal products are covered by
 * one or more patents listed at http://www.pivotal.io/patents.
 *=========================================================================
 */
package com.gemstone.persistence.logging;

/**
 * A Handler exports LogRecords to some destination.  It can be
 * configured to ignore log records that are below a given level.  It
 * can also a have formatter for formatting the log records before
 * exporting the to the destination.
 */
public abstract class Handler {
  
  /** The minimum level for this handler.  Any records below this
   * level are ignored. */
  private Level level;

  /** Used to format the log records */
  private Formatter formatter;

  /**
   * Creates a new <code>Handler</code> with Level.ALL and no
   * formatter.
   */
  protected Handler() {
    this.level = Level.ALL;
    this.formatter = null;
  }

  /**
   * Closes this Handler and frees all of its resources
   */
  public abstract void close();

  /**
   * Flushes an buffered output
   */
  public abstract void flush();

  /**
   * Returns the formatter for this handler
   */
  public Formatter getFormatter() {
    return(this.formatter);
  }

  /**
   * Sets the formatter for this handler
   */
  public void setFormatter(Formatter formatter) {
    this.formatter = formatter;
  }

  /**
   * Returns the level below which this handler ignores
   */
  public Level getLevel() {
    return(this.level);
  }

  /**
   * Sets the level below which this handler ignores
   */
  public void setLevel(Level level) {
    this.level = level;
  }

  /**
   * Returns <code>true</code> if a log record will be handled by this
   * handler.
   */
  public boolean isLoggable(LogRecord record) {
    if(record.getLevel().intValue() >= this.getLevel().intValue()) {
      return(true);
    } else {
      return(false);
    }
  }

  /**
   * Publishes a log record to this handler
   */
  public abstract void publish(LogRecord record);

}