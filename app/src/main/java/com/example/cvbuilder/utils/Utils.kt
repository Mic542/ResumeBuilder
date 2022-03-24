package com.example.cvbuilder.utils

import androidx.room.TypeConverter
import com.example.cvbuilder.data.EducationDetail
import com.example.cvbuilder.data.ProjectDetail
import com.example.cvbuilder.data.SkillDetail
import com.example.cvbuilder.data.WorkExperience
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*

class Utils {
    // Work Experience to JSON and vice verse
    @TypeConverter
    fun storedStringToWorkExperienceJSON(data: String?): List<WorkExperience?>? {
        val gson = Gson()
        if (data == null) {
            return Collections.emptyList()
        }
        val listType: Type = object : TypeToken<List<WorkExperience?>?>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun workExperienceJSONToStoredString(myObjects: List<WorkExperience?>?): String? {
        val gson = Gson()
        return gson.toJson(myObjects)
    }

    // Skill Detail to JSON and vice verse
    @TypeConverter
    fun storedStringToSkillJSON(data: String?): List<SkillDetail?>? {
        val gson = Gson()
        if (data == null) {
            return Collections.emptyList()
        }
        val listType: Type = object : TypeToken<List<SkillDetail?>?>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun skillJSONToStoredString(myObjects: List<SkillDetail?>?): String? {
        val gson = Gson()
        return gson.toJson(myObjects)
    }

    // Education detail to JSON and vice verse
    @TypeConverter
    fun storedStringToEducationDetailJSON(data: String?): List<EducationDetail?>? {
        val gson = Gson()
        if (data == null) {
            return Collections.emptyList()
        }
        val listType: Type = object : TypeToken<List<EducationDetail?>?>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun educationDetailJSONToStoredString(myObjects: List<EducationDetail?>?): String? {
        val gson = Gson()
        return gson.toJson(myObjects)
    }

    // Project List to JSON and vice verse
    @TypeConverter
    fun storedStringToProjectListJSON(data: String?): List<ProjectDetail?>? {
        val gson = Gson()
        if (data == null) {
            return Collections.emptyList()
        }
        val listType: Type = object : TypeToken<List<ProjectDetail?>?>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun projectListJSONToStoredString(myObjects: List<ProjectDetail?>?): String? {
        val gson = Gson()
        return gson.toJson(myObjects)
    }
}