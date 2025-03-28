package nl.rabobank.org_users_rest.repository

import com.fasterxml.jackson.databind.ObjectMapper
import nl.rabobank.org_users_rest.entity.User
import java.nio.file.Paths

class FsUsersRepository(private val objectMapper: ObjectMapper, filepath: String) : UsersRepository {
    private val source = Paths.get(filepath).toFile();

    private val data = if (source.exists()) {
        objectMapper.readValue(source, Array<User>::class.java).toList().toMutableList();
    } else {
        mutableListOf()
    }

    override fun findAll(): List<User> = data;

    override fun findUserById(id: Int): User? = data.firstOrNull { it.id == id }

    override fun addOne(newUser: User): List<User> {
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

    override fun save(data: List<User>): String {
        objectMapper.writeValue(source, data);

        return "Ok";
    }
}