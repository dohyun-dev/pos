package com.dohyundev.pos.core.catalog.menu_group.application

import com.dohyundev.pos.core.catalog.menu_group.domain.MenuGroup
import com.dohyundev.pos.core.catalog.menu_group.domain.MenuGroupRepository
import com.dohyundev.pos.core.catalog.menu_group.dto.MenuGroupCommand
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class MenuGroupCommandService(
    private val menuGroupRepository: MenuGroupRepository
) {
    fun create(command: MenuGroupCommand.Create): MenuGroup {
        val menuGroup = MenuGroup(command.name, command.priority, command.isVisible)
        return menuGroupRepository.save(menuGroup)
    }

    fun update(command: MenuGroupCommand.Update): MenuGroup {
        val menuGroup = menuGroupRepository.findById(command.menuGroupId).orElseThrow { IllegalArgumentException("MenuGroup not found") }
        command.name?.let { menuGroup.name = it }
        command.priority?.let { menuGroup.priority = it }
        command.isVisible?.let { menuGroup.isVisible = it }
        return menuGroup
    }

    fun delete(command: MenuGroupCommand.Delete) {
        menuGroupRepository.deleteById(command.menuGroupId)
    }
}