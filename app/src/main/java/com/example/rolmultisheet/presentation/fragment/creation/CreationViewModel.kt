package com.example.rolmultisheet.presentation.fragment.creation

import androidx.lifecycle.*
import com.example.rolmultisheet.R
import com.example.rolmultisheet.domain.model.Character
import com.example.rolmultisheet.domain.model.Job
import com.example.rolmultisheet.domain.model.Race
import com.example.rolmultisheet.domain.model.form.CharacterFormValidator
import com.example.rolmultisheet.domain.model.form.util.*
import com.example.rolmultisheet.domain.repository.AppRepository
import com.example.rolmultisheet.domain.valueObject.StringResource
import com.example.rolmultisheet.presentation.util.event.Event
import com.example.rolmultisheet.presentation.util.functions.combineWith
import kotlinx.coroutines.launch
import java.util.*

private const val CURRENT_DICES: String = "CURRENT_DICES"

class CreationViewModel(private val appRepository: AppRepository, handle: SavedStateHandle) :
    ViewModel() {

    val raceList: LiveData<List<Race>> = appRepository.queryAllRaces()
    val jobList: LiveData<List<Job>> = appRepository.queryAllJobs()
    val raceIndex: MutableLiveData<Int> = MutableLiveData(0)
    var jobIndex: MutableLiveData<Int> = MutableLiveData(0)
    val currentDices: MutableLiveData<String> =
        handle.getLiveData(CURRENT_DICES, "15, 14, 13, 12, 10, 8 ")

    val currentRace = raceList.combineWith(raceIndex) { list, position ->
        if (list != null && position != null) {
            list[position]
        } else {
            Race(0, "", 0, 0.0, 0)
        }
    }

    val currentJob = jobList.combineWith(jobIndex) { list, position ->
        if (list != null && position != null) {
            list[position]
        } else {
            Job(0, "", "", "", "")
        }
    }

    private val _onInvalidName: MutableLiveData<Event<StringResource>> = MutableLiveData()
    val onInvalidName: LiveData<Event<StringResource>>
        get() = _onInvalidName

    private val _onInvalidStrength: MutableLiveData<Event<StringResource>> = MutableLiveData()
    val onInvalidStrength: LiveData<Event<StringResource>>
        get() = _onInvalidStrength

    private val _onInvalidDexterity: MutableLiveData<Event<StringResource>> = MutableLiveData()
    val onInvalidDexterity: LiveData<Event<StringResource>>
        get() = _onInvalidDexterity

    private val _onInvalidConstitution: MutableLiveData<Event<StringResource>> = MutableLiveData()
    val onInvalidConstitution: LiveData<Event<StringResource>>
        get() = _onInvalidConstitution

    private val _onInvalidIntelligence: MutableLiveData<Event<StringResource>> = MutableLiveData()
    val onInvalidIntelligence: LiveData<Event<StringResource>>
        get() = _onInvalidIntelligence

    private val _onInvalidWisdom: MutableLiveData<Event<StringResource>> = MutableLiveData()
    val onInvalidWisdom: LiveData<Event<StringResource>>
        get() = _onInvalidWisdom

    private val _onInvalidCharisma: MutableLiveData<Event<StringResource>> = MutableLiveData()
    val onInvalidCharisma: LiveData<Event<StringResource>>
        get() = _onInvalidCharisma

    private val _onClose: MutableLiveData<Event<Boolean>> = MutableLiveData()
    val onClose: LiveData<Event<Boolean>>
        get() = _onClose

    fun validateFields(
        characterName: String?,
        characterStrength: String?,
        characterDexterity: String?,
        characterConstitution: String?,
        characterIntelligence: String?,
        characterWisdom: String?,
        characterCharisma: String?,
    ): Boolean {
        try {
            return CharacterFormValidator(
                characterName,
                characterStrength?.toIntOrNull(),
                characterDexterity?.toIntOrNull(),
                characterConstitution?.toIntOrNull(),
                characterIntelligence?.toIntOrNull(),
                characterWisdom?.toIntOrNull(),
                characterCharisma?.toIntOrNull(),
            ).validate()
        } catch (e: FormNameException) {
            _onInvalidName.value = Event(StringResource(R.string.form_null_blank_exception))
        } catch (e: FormStrengthException) {
            _onInvalidStrength.value = Event(StringResource(R.string.form_invalid_range_exception))
        } catch (e: FormDexterityException) {
            _onInvalidDexterity.value = Event(StringResource(R.string.form_invalid_range_exception))
        } catch (e: FormConstitutionException) {
            _onInvalidConstitution.value =
                Event(StringResource(R.string.form_invalid_range_exception))
        } catch (e: FormIntelligenceException) {
            _onInvalidIntelligence.value =
                Event(StringResource(R.string.form_invalid_range_exception))
        } catch (e: FormWisdomException) {
            _onInvalidWisdom.value = Event(StringResource(R.string.form_invalid_range_exception))
        } catch (e: FormCharismaException) {
            _onInvalidCharisma.value = Event(StringResource(R.string.form_invalid_range_exception))
        }
        return false
    }

    fun save(
        characterName: String,
        characterStrength: String,
        characterDexterity: String,
        characterConstitution: String,
        characterIntelligence: String,
        characterWisdom: String,
        characterCharisma: String,
    ) {
        val raceId = currentRace.value!!.raceId
        val jobId = currentJob.value!!.jobId
        viewModelScope.launch {
            appRepository.insertCharacter(
                Character(
                    0,
                    raceId,
                    jobId,
                    characterName,
                    characterStrength.toInt(),
                    characterDexterity.toInt(),
                    characterConstitution.toInt(),
                    characterIntelligence.toInt(),
                    characterWisdom.toInt(),
                    characterCharisma.toInt(),
                )
            )
            _onClose.value = Event(true)
        }
    }

    fun throwDices() {
        val random = Random()
        val throws = mutableListOf<Int>()
        val currentThrows = mutableListOf<Int>()

        for (i in 0..5) {
            for (j in 0..4) {
                currentThrows.add(random.nextInt(6) + 1)
            }
            currentThrows.sortDescending()
            currentThrows.removeLast()
            currentThrows.removeLast()
            throws.add(currentThrows.reduce { n1, n2 ->
                n1 + n2
            })
            currentThrows.clear()
        }

        currentDices.value = throws.sortedByDescending { i -> i }.joinToString { number ->
            number.toString()
        }
    }
}
