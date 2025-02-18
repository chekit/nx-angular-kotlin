package nl.rabobank.org_users_rest.repository

import com.fasterxml.jackson.databind.ObjectMapper
import nl.rabobank.org_users_rest.model.User
import java.nio.file.Paths

class FsUsersRepository(private val objectMapper: ObjectMapper, filepath: String) : UsersRepository {
    private val source = Paths.get(filepath).toFile();

    private val data = if (source.exists()) {
        objectMapper.readValue(source, Array<User.Entity>::class.java).toList().toMutableList();
    } else {
        mutableListOf()
    }

    override fun findAll(): MutableList<User.Entity> = data;

    override fun findById(id: Int): User.Entity? = data.firstOrNull { it.id == id }

    override fun addOne(newUser: User.Entity): MutableList<User.Entity> {
        data.addLast(newUser)
        save(data);

        return data;
    }

    override fun updateOne(updatedUser: User.Entity): User.Entity {
        data.replaceAll { if (it.id == updatedUser.id) updatedUser else it }
        save(data);

        return updatedUser;
    }

    override fun deleteOne(id: Int): String? {
        val user = data.firstOrNull { it.id == id } ?: return null;

        data.remove(user);

        return save(data);
    }

    override fun save(data: MutableList<User.Entity>): String {
        objectMapper.writeValue(source, data);

        return "Ok";
    }
}