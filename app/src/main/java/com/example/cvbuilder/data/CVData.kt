package com.example.cvbuilder.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CVData(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "image_uri") var imageUri: String?,
    @ColumnInfo(name = "save_name") var saveName: String?,
    @ColumnInfo(name = "full_name") var fullName: String?,
    @ColumnInfo(name = "mobile_phone") var mobilePhone: String?,
    @ColumnInfo(name = "email_address") var emailAddress: String?,
    @ColumnInfo(name = "residence_address") var residenceAddress: String?,
    @ColumnInfo(name = "career_objective") var careerObjective: String?,
    @ColumnInfo(name = "years_experience") var yearsExperience: Int?,
    @ColumnInfo(name = "work_summary") var workSummary: List<WorkExperience>?,
    @ColumnInfo(name = "skill") var skill: List<SkillDetail>?,
    @ColumnInfo(name = "education") var education: List<EducationDetail>?,
    @ColumnInfo(name = "project") var project: List<ProjectDetail>?,
    )
