package com.shpend.app.web;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;

import com.shpend.app.domain.Role;

public interface RegisterRole {
	String dashboard(@PathVariable long userId,ModelMap map, HttpServletResponse response) throws IOException;

}
