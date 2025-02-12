package nl.rabobank.org_users_rest.repository

import com.fasterxml.jackson.databind.ObjectMapper
import nl.rabobank.org_users_rest.model.User
import nl.rabobank.org_users_rest.model.UserDto
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Repository
import java.nio.file.Paths

//@Repository
//@Profile("local")
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

    override fun addOne(newUser: User): MutableList<User> {
        data.addLast(newUser)
        save(data);

        return data;
    }

    override fun updateOne(index: Int, updatedUser: User): User {
        data[index] = updatedUser;
        save(data);

        return updatedUser;
    }

    override fun deleteOne(index: Int): String {
        data.removeAt(index);

        return  save(data);
    }

    override fun save(data: MutableList<User>): String {
        objectMapper.writeValue(source, data);

        return "Ok";
    }

    override fun findIndexById(id: Int): Int {
        return data.indexOfFirst { it.id == id }
    }
}