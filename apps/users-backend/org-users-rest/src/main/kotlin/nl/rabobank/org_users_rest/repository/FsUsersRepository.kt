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

    override fun findAll(): MutableList<User> = data;

    override fun findById(id: Int): User? =  data.firstOrNull { it.id == id }

    override fun addOne(newUser: User): MutableList<User> {
        data.addLast(newUser)
        save(data);

        return data;
    }

    override fun updateOne(updatedUser: User): User {
        data.replaceAll { if (it.id == updatedUser.id) updatedUser else it }
        save(data);

        return updatedUser;
    }

    override fun deleteOne(id: Int): String? {
        val user = data.firstOrNull { it.id == id } ?: return null;

        data.remove(user);

        return save(data);
    }

    override fun save(data: MutableList<User>): String {
        objectMapper.writeValue(source, data);

        return "Ok";
    }
}