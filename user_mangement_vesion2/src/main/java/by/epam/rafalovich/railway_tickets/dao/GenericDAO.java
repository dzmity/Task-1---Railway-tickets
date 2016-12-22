package by.epam.rafalovich.railway_tickets.dao;

import java.util.Collection;

import by.epam.rafalovich.railway_tickets.entity.Entity;
import by.epam.rafalovich.railway_tickets.exception.DAOException;

public interface GenericDAO<T extends Entity> {

	/**
     * Add model object to the database operation
     * @param t
     *        Model object that must be added to the database
     * @throws DAOException
     *        if a {@link java.sql.SQLException} occurs;
     */
    void create(T t) throws DAOException;

    /**
     * Find model object in the database operation
     * @param id
     *        id of model object that must be found in the database
     * @throws DAOException
     *        if a {@link java.sql.SQLException} occurs;
     *        if model object not found;
     *        if this operation unsupported in specific DAO.
     */
    T findById(long id) throws DAOException;

    /**
     * Update model object in the database operation
     * @param id
     *        id of model object that must be updated in the database
     * @param t
     *        New model object that must be added to the database replaced
     *        the old by this {@code id}
     * @throws DAOException
     *        if a {@link java.sql.SQLException} occurs;
     *        if this operation unsupported in specific DAO.
     */
    void updateById(long id, T t) throws DAOException;

    /**
     * Delete model object from the database operation
     * @param id
     *        id of model object that must be deleted from the database
     * @throws DAOException
     *        if a {@link java.sql.SQLException} occurs;
     *        if this operation unsupported in specific DAO.
     */
    void deleteById(long id) throws DAOException;

    /**
     * Find all model objects in the database operation
     * @return {@link Collection} of model objects
     * @throws DAOException
     *        if a {@link java.sql.SQLException} occurs;
     *        if this operation unsupported in specific DAO.
     */
    Collection<T> findAll() throws DAOException;
}
