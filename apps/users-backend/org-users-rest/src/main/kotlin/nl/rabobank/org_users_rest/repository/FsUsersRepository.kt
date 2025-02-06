package nl.rabobank.org_users_rest.repository

import com.fasterxml.jackson.databind.ObjectMapper
import nl.rabobank.org_users_rest.model.User
import java.nio.file.Paths

class FsUsersRepository(private val objectMapper: ObjectMapper) : UsersRepository {
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

    override fun findAll(): MutableList<User> {
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

    override fun save(data: MutableList<User>): String {
        objectMapper.writeValue(source, data);

        return "Ok";
    }
}