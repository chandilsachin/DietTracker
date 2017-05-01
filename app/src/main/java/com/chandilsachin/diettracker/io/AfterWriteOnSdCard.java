package com.chandilsachin.diettracker.io;

import java.io.File;

/**
 * <h1>@BBI Docs</h1>
 * <h1>public interface AfterWriteOnSdCard</h1>
 * <p>Interface definition for a callback to be invoked when file has been written on SD card.</p>
 *
 * @see SdCardManager.writeOnSdCard()
 */
public interface AfterWriteOnSdCard
{

    /**
     * <h1>@BBI Docs</h1>
     * <h1>public void AfterWriteOnSdCard(File writtenFile);</h1>
     * <p>This callback method is called when file is written on SD Card.</p>
     *
     * @param writtenFile - File that has been written.
     * @see SdCardManager.writeOnSdCard()
     */
    public void OnWriteComplete(File writtenFile);
}
