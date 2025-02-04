package nl.rabobank.org_users_rest.repository

import java.io.DataInputStream
import java.io.FileInputStream


class FsUsersRepository {
    private val file = "src/main/resources/static/users.json";
    private val reader = DataInputStream(FileInputStream(file));

     var data: String? = null;

    init {
        val nBytesToRead: Int = reader.available();

        if (nBytesToRead > 0) {
            val bytes = ByteArray(nBytesToRead);
            reader.read(bytes);
            data = String(bytes);
        }
    }
}