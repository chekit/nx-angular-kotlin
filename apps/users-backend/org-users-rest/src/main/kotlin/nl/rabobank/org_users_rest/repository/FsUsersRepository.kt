package nl.rabobank.org_users_rest.repository

import com.fasterxml.jackson.databind.ObjectMapper
import nl.rabobank.org_users_rest.model.User
import java.io.DataInputStream
import java.io.FileInputStream
import java.nio.file.Paths

class FsUsersRepository(objectMapper: ObjectMapper) : UsersRepository {
    private val file = "src/main/resources/static/users.json";

    private val source by lazy {
        Paths.get(file).toFile()
    };

    private val data by lazy {
        if (source.exists()) {
            objectMapper.readValue(source, Array<User>::class.java).toList().toMutableList();
        } else {
            mutableListOf()
        }
    }

    override fun getAll(): MutableList<User> {
        return data;
    }

    override fun addOne(): MutableList<User> {
        return data;
    }

    override fun updateOne(): User {
        return data[0];
    }

    override fun deleteOne(): String {
        return "OK";
    }
}